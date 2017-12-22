/**
 * @license
 * Copyright 2017 The FOAM Authors. All Rights Reserved.
 *     http://www.apache.org/licenses/LICENSE-2.0
 */

package foam.dao.index;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class PersistedIndex
    extends ProxyIndex
    implements Closeable
{
  protected final File file_;
  protected FileInputStream fis_;
  protected FileOutputStream fos_;

  protected ByteArrayOutputStream bos_ = new ByteArrayOutputStream();
  protected ObjectOutputStream oos_ = new ObjectOutputStream(bos_);

  public PersistedIndex(String filename, Index index) throws IOException {
    this.file_ = new File(filename).getAbsoluteFile();
    if ( ! file_.exists() ) {
      if ( ! this.file_.createNewFile() ) {
        throw new IOException("Unable to create file: " + filename);
      }
    }
    this.fis_ = new FileInputStream(this.file_);
    this.fos_ = new FileOutputStream(this.file_);
    setDelegate(index);
  }

  @Override
  public Object wrap(Object state) {
    synchronized ( file_ ) {
      try {
        oos_.writeObject(state);
        oos_.flush();
        bos_.writeTo(fos_);
        oos_.reset();
        return fos_.getChannel().position();
      } catch (Throwable t) {
        throw new RuntimeException(t);
      }
    }
  }

  @Override
  public Object unwrap(Object state) {
    synchronized ( file_ ) {
      try {
        long position = (long) state;
        fis_.getChannel().position(position);
        ObjectInputStream iis = new ObjectInputStream(fis_);
        return iis.readObject();
      } catch (Throwable t) {
        throw new RuntimeException(t);
      }
    }
  }

  @Override
  public void close() throws IOException {
    IOUtils.closeQuietly(oos_);
    IOUtils.closeQuietly(fos_);
    IOUtils.closeQuietly(fis_);
  }
}