/**
 * 
 */
package pl.doa.wicket.angularjs.test;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import pl.doa.wicket.angularjs.form.AngularField;
import pl.doa.wicket.angularjs.form.AngularForm;
import pl.doa.wicket.angularjs.form.AngularResponse;
import pl.doa.wicket.angularjs.form.AngularSubmitButton;

/**
 * @author activey
 * 
 */
public class TestForm extends AngularForm<TestObject> {

	public TestForm(String id, IModel<TestObject> formInputModel) {
		super(id, formInputModel, new TestObjectSerializer());
	}

	public TestForm(String id, TestObject formInput) {
		super(id, formInput, new TestObjectSerializer());
	}

	@Override
	protected void onInitializeForm() {
		add(new AngularField("field_test1", new Model<String>("test1")));
		add(new AngularField("field_test2", new Model<String>("test2")));

		add(new AngularSubmitButton("submit"));
	}

	@Override
	protected void processResponse(AngularResponse angularResponse) {
		angularResponse.alert("aaaaa!");


        TestObject ble = getModelObject();
        ble.getTesrt

	}

	@Override
	public Class<TestObject> getTypeClass() {
		return TestObject.class;
	}
}
