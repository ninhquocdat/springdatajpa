package vn.hcmute.web.logic.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import vn.hcmute.web.logic.command.AbstractCommand;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Admin on 18/6/2017.
 */
public class RequestUtil {
    private static final Logger log = Logger.getLogger(RequestUtil.class);
    /**
     * init the SearchBean from request for pageing user list.
     * @param request HttpServletRequest
     * @param bean AbstractCommand
     */
    public static void initSearchBean(HttpServletRequest request, AbstractCommand bean) {
        if (bean != null){
            String sortExpression = request.getParameter(new ParamEncoder(bean.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_SORT));
            String sortDirection = request.getParameter(new ParamEncoder(bean.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_ORDER));
            String sPage = request.getParameter(new ParamEncoder(bean.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_PAGE));
            Integer page = 1;
            if (StringUtils.isNotBlank(sPage)) {
                try {
                    page = Integer.valueOf(sPage);
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
            bean.setPage(page);
            bean.setFirstItem((bean.getPage() - 1) * bean.getMaxPageItems());
            bean.setSortExpression(sortExpression);
            bean.setSortDirection(sortDirection);
        }
    }
}
