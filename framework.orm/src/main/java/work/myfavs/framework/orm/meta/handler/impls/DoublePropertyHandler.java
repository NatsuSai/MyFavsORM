/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package work.myfavs.framework.orm.meta.handler.impls;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import work.myfavs.framework.orm.meta.handler.PropertyHandler;

public class DoublePropertyHandler
    extends PropertyHandler<Double> {

  private boolean isPrimitive;

  public DoublePropertyHandler() {

  }

  public DoublePropertyHandler(boolean isPrimitive) {

    this.isPrimitive = isPrimitive;
  }

  @Override
  public Double convert(ResultSet rs, String columnName, Class<Double> clazz)
      throws SQLException {

    double i = rs.getDouble(columnName);
    if (rs.wasNull()) {
      if (isPrimitive) {
        return 0.0d;
      } else {
        return null;
      }
    }
    return i;
  }

  @Override
  public void addParameter(PreparedStatement ps, int paramIndex, Double param)
      throws SQLException {

    if (param == null) {
      ps.setNull(paramIndex, Types.DOUBLE);
      return;
    }
    ps.setDouble(paramIndex, param);
  }

}
