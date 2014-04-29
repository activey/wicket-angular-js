/**
 * 
 */
package pl.doa.wicket.angularjs.serializer.impl;

import java.io.Serializable;

import org.apache.wicket.ajax.json.JSONException;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

import pl.doa.wicket.angularjs.serializer.IModelSerializer;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * @author activey
 * 
 */
public abstract class DefaultObjectSerializer<T extends Object> implements
		IModelSerializer<T>, Serializable {

	private static final long serialVersionUID = 1L;
	protected static final String DEFAULT_ANGULAR_MODEL_PROPERTY = "data";

	@Override
	public JsonElement serialize(IModel<T> model) throws JSONException {
		JsonObject json = new JsonObject();
		Object value = model.getObject();
		if (value != null) {
			JsonElement valueJson = new Gson().toJsonTree(value);
			return valueJson;
		}
		return json;
	}

	public abstract Class<T> getTypeClass();

	@Override
	public T deserialize(JsonObject json) throws JSONException {
		Gson serializer = new Gson();
		try {
			return serializer.fromJson(json, getTypeClass());
		} catch (Exception e) {
			throw new JSONException(e);
		}
	}

	public static IModel<String> getAngularModelProperty() {
		return new AbstractReadOnlyModel<String>() {

			@Override
			public String getObject() {
				return DEFAULT_ANGULAR_MODEL_PROPERTY;
			}
		};
	}

}
