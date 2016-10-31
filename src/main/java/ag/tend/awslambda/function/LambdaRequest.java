package ag.tend.awslambda.function;

import ag.tend.awslambda.util.Contants;
import ag.tend.awslambda.util.ErrorCode;
import ag.tend.awslambda.util.Utils;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.lambda.AWSLambdaClient;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;


public class LambdaRequest {
    private static final Log logger = LogFactory.getLog(LambdaRequest.class);
    private static AWSCredentials credentials;
    private static AWSLambdaClient lambdaClient;

    static {
        // Init lambdaClient
        credentials = new BasicAWSCredentials(Contants.awsAccessKeyId, Contants.awsSecretAccessKey);
        lambdaClient = new AWSLambdaClient(credentials);
        lambdaClient.setRegion(Contants.region);
    }

    /**
     * The entry point into the AWS lambda function.
     */
    public static JSONObject invoke(String message) {
        JSONObject result = defaultResult();
        try {
            InvokeRequest invokeRequest = new InvokeRequest();
            invokeRequest.setFunctionName(Contants.functionName);
            JSONObject obj = new JSONObject();
            obj.put("sender", message);
            invokeRequest.setPayload(obj.toJSONString());

            InvokeResult invoke = lambdaClient.invoke(invokeRequest);
            if (invoke.getStatusCode() != 200) {
                // Connection Error
                result.put("status", ErrorCode.CONNECTION_ERR);
                result.put("message", Utils.byteBufferToString(invoke.getPayload()));
            } else {
                // success
                result.put("status", ErrorCode.SUCCESS);
                result.put("message", Utils.byteBufferToString(invoke.getPayload()));
            }
        } catch (Exception e) {
            // AWS Service  Error
            logger.error(e.getMessage());
            result.put("status", ErrorCode.AWS_ERR);
            result.put("message", e.getMessage());
        }
        return result;
    }


    public static JSONObject defaultResult() {
        JSONObject result = new JSONObject();
        result.put("status", ErrorCode.UNHANDLED_ERR);
        result.put("message", "");
        return result;
    }
}