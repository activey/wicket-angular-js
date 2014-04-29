/**
 * 
 */
package pl.doa.wicket.angularjs.serializer.impl;

import org.apache.wicket.ajax.json.JSONException;
import org.apache.wicket.model.IModel;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * @author activey
 * 
 */
public class DefaultStringSerializer extends DefaultObjectSerializer<String> {

	@Override
	public JsonElement serialize(IModel<String> model) throws JSONException {
		JsonObject json = new JsonObject();
		Object value = model.getObject();
		if (value != null) {
			json.addProperty(DEFAULT_ANGULAR_MODEL_PROPERTY, value.toString());
			return json;
		}
		return json;
	}

	@Override
	public String deserialize(JsonObject json) throws JSONException {
		String modelValue = json.get(DEFAULT_ANGULAR_MODEL_PROPERTY)
				.getAsString();
		return modelValue;
	}

	@Override
	public Class<String> getTypeClass() {
		return String.class;
	}
}
