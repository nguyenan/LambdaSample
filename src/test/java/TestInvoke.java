
import ag.tend.awslambda.function.LambdaRequest;
import org.json.simple.JSONObject;

/**
 * Created by annguyen on 31/10/16.
 */
public class TestInvoke {
    public static void main(String[] args) {
        JSONObject invoke = LambdaRequest.invoke("an.nguyenhoang");
        System.out.println(invoke.toJSONString());
    }
}
