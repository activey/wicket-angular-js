package pl.doa.wicket.angularjs.behavior;

import org.apache.wicket.request.cycle.RequestCycle;

public interface IAngularController {

	public String getControllerName();

	public void invoke(RequestCycle requestCycle);
}
