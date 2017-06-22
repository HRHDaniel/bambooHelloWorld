package helloworld;

import com.atlassian.bamboo.build.logger.BuildLogger;
import com.atlassian.bamboo.task.TaskContext;
import com.atlassian.bamboo.task.TaskException;
import com.atlassian.bamboo.task.TaskResult;
import com.atlassian.bamboo.task.TaskResultBuilder;
import com.atlassian.bamboo.task.TaskType;

public class ExampleTask implements TaskType {

	@Override
	public TaskResult execute(final TaskContext taskContext) throws TaskException {
		final BuildLogger buildLogger = taskContext.getBuildLogger();

		final String accessKey = taskContext.getConfigurationMap().get("accessKey");
		final String secretKey = taskContext.getConfigurationMap().get("secretKey");


		buildLogger.addBuildLogEntry(accessKey);
		buildLogger.addBuildLogEntry(secretKey);

		return TaskResultBuilder.create(taskContext).success().build();
	}
}
