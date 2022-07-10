package utilities;

import java.util.Map;

import static java.lang.String.format;
import static java.util.Objects.nonNull;

public class EndpointBuilder {

    public static final String BOOKING = "/booking";
    public static final String BOOKING_ID = "/booking/%s";

    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String CHECKIN = "checkin";
    private static final String CHECKOUT = "checkout";

    private static final String PARAM_VALUE = "=%s&";

    private static String buildUriParams(String key) {
        String uriParam = "";
        StringBuilder uriParams = new StringBuilder(uriParam);

        switch (key) {
            case FIRSTNAME -> uriParams.append(format("%s%s", FIRSTNAME, PARAM_VALUE));
            case LASTNAME -> uriParams.append(format("%s%s", LASTNAME, PARAM_VALUE));
            case CHECKIN -> uriParams.append(format("%s%s", CHECKIN, PARAM_VALUE));
            case CHECKOUT -> uriParams.append(format("%s%s", CHECKOUT, PARAM_VALUE));
        }

        return String.valueOf(uriParams);
    }

    public static String buildBookingEndpoint(Map<String, String> params) {
        StringBuilder endPoint = new StringBuilder(format("%s?", BOOKING));
        String value;

        if (nonNull(params)) {
            for (Map.Entry<String, String> pair : params.entrySet()) {
                String uriParams = buildUriParams(pair.getKey());
                value = pair.getValue();
                endPoint.append(format(uriParams, value));
            }
        }
        return String.valueOf(endPoint);
    }
}
