/**
 * 
 */
package pl.doa.wicket.angularjs.form;

import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.TextRequestHandler;

/**
 * @author activey
 * 
 */
public class AngularResponse {

    private RequestCycle cycle;

    public AngularResponse(RequestCycle cycle) {
        this.cycle = cycle;
    }

    public void eval(String javascript) {
        cycle.scheduleRequestHandlerAfterCurrent(new TextRequestHandler(
            "text/javascript", "UTF-8", OnDomReadyHeaderItem.forScript(javascript).getJavaScript().toString()));
    }

    public void alert(String alertText) {

        eval("alert('" + alertText + "')");

    }
}
