package pl.doa.wicket.angularjs.test;

import org.apache.wicket.ajax.json.JSONException;
import org.apache.wicket.model.IModel;

import pl.doa.wicket.angularjs.serializer.impl.DefaultObjectSerializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class TestObjectSerializer extends DefaultObjectSerializer<TestObject> {

	@Override
	public JsonElement serialize(IModel<TestObject> model) throws JSONException {
		return super.serialize(model);
	}

	@Override
	public TestObject deserialize(JsonObject json) throws JSONException {
		return super.deserialize(json);
	}

	@Override
	public Class<TestObject> getTypeClass() {
		return TestObject.class;
	}

}
