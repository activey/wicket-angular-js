package pl.doa.wicket.angularjs.serializer;

import org.apache.wicket.ajax.json.JSONException;
import org.apache.wicket.model.IModel;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public interface IModelSerializer<T extends Object> {

	public JsonElement serialize(IModel<T> model) throws JSONException;

	public T deserialize(JsonObject json) throws JSONException;
}
