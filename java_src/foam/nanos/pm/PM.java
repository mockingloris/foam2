/**
 * @license
 * Copyright 2017 The FOAM Authors. All Rights Reserved.
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package foam.nanos.pm;

public class PM {
  protected Class  cls_;
  protected String name_;
  protected long   startTime_;
  protected long   endTime_;

  public PM(Class cls, String name) {
    cls_       = cls;
    name_      = name;
    startTime_ = System.nanoTime();
  }

  public void end(X x) {
    PMLogger logger = x.get('pmLogger');
    endTime_ = System.nanoTime();
    x.log(this);
  }

  public Class getClassType() {
    return cls_;
  }

  public String getName() {
    return name_;
  }

  public long getStartTime() {
    return startTime_;
  }

  public long getTime() {
    return endTime_ - startTime_;
  }
}
