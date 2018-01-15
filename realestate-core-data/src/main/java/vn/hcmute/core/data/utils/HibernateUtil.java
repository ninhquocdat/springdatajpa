package vn.hcmute.core.data.utils;

import vn.hcmute.core.data.common.Constant;

import java.util.Map;


public class HibernateUtil {

    private HibernateUtil() {
    }

    public static Object[] buildNameQuery(Map<String, Object> property, String whereClause, String sortExpression, String sortDirection) {
        StringBuilder nameQuery = new StringBuilder();
        if (property != null && property.size() > 0) {
            String[] params = new String[property.size()];
            Object[] values = new Object[property.size()];
            int i = 0 ;
            for(Map.Entry item: property.entrySet()) {
                params[i] = (String) item.getKey();
                values[i] = item.getValue();
                i++;
            }
            for (int i1 = 0; i1 < params.length ; i1++) {
                if (values[i1] instanceof String) {
                    nameQuery.append(" AND ").append("upper(A."+params[i1]+") LIKE upper('%' || :"+params[i1]+" || '%') ");
                } else {
                    nameQuery.append(" AND A."+params[i1]+"= :"+params[i1]+" ");
                }
            }
            if (whereClause != null) {
                nameQuery.append(whereClause);
            }
            if (sortExpression != null && sortDirection != null) {
                nameQuery.append(" ORDER BY ").append(sortExpression);
                nameQuery.append(" " +(sortDirection.equals(Constant.SORT_ASC)?"asc":"desc"));
            }
            return new Object[]{nameQuery, params, values};
        }
        return new Object[]{nameQuery.toString()};
    }
}
