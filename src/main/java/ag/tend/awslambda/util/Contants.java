package ag.tend.awslambda.util;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;

/**
 * Created by annguyen on 31/10/16.
 */
public class Contants {
    private static final ConfigReader configReader = ConfigReader.getInstance();

    // user: tend.ag.lambda
    public static final String awsAccessKeyId = configReader.getPropValues("awsAccessKeyId");
    public static final String awsSecretAccessKey = configReader.getPropValues("awsSecretAccessKey");
    public static final String regionName = configReader.getPropValues("regionName");
    public static Region region = Region.getRegion(Regions.fromName(regionName));

    //functionName
    public static final String functionName = "httpEndpointFunction";
}
