package helloworld;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.atlassian.bamboo.collections.ActionParametersMap;
import com.atlassian.bamboo.task.AbstractTaskConfigurator;
import com.atlassian.bamboo.task.TaskDefinition;
import com.atlassian.bamboo.utils.error.ErrorCollection;
import com.atlassian.util.concurrent.Nullable;

public class ExampleTaskConfigurator extends AbstractTaskConfigurator {

	public Map<String, String> generateTaskConfigMap(final ActionParametersMap params,
			@Nullable final TaskDefinition previousTaskDefinition) {
		final Map<String, String> config = super.generateTaskConfigMap(params, previousTaskDefinition);

		config.put("accessKey", params.getString("accessKey"));
		config.put("secretKey", params.getString("secretKey"));

		return config;
	}

	public void validate(final ActionParametersMap params, final ErrorCollection errorCollection) {
		super.validate(params, errorCollection);

		final String accessKeyValue = params.getString("accessKey");
		if (StringUtils.isEmpty(accessKeyValue)) {
			errorCollection.addError("accessKey", "AccessKey is Required");
		}
		final String secretKeyValue = params.getString("secretKey");
		if (StringUtils.isEmpty(secretKeyValue)) {
			errorCollection.addError("secretKey", "SecretKey is Required");
		}
	}

	@Override
	public void populateContextForCreate(final Map<String, Object> context) {
		super.populateContextForCreate(context);

		context.put("accessKey", "");
		context.put("secretKey", "");
	}

	@Override
	public void populateContextForEdit(final Map<String, Object> context, final TaskDefinition taskDefinition) {
		super.populateContextForEdit(context, taskDefinition);

		context.put("accessKey", taskDefinition.getConfiguration().get("accessKey"));
		context.put("secretKey", taskDefinition.getConfiguration().get("secretKey"));
	}
}
