/**
 * @license
 * Copyright 2018 The FOAM Authors. All Rights Reserved.
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package foam.nanos.dig;

import foam.core.FObject;
import foam.core.X;
import foam.dao.JDAO;
import foam.dao.ProxyDAO;
import foam.nanos.auth.User;

public class DUGDAO
    extends ProxyDAO
{
  public DUGDAO(X x) {
    setX(x);
    setDelegate(new JDAO(x, DUG.getOwnClassInfo(), "dugs"));
  }

  @Override
  public FObject put_(X x, FObject obj) {
    User user = (User) x.get("user");
    if ( user == null ) {
      throw new RuntimeException("User is not logged in");
    }

    ((DUG) obj).setOwner(user.getId());
    ((DUG) obj).execute(x);
    return super.put_(x, obj);
  }
}
