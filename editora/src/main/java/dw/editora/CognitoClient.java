package dw.editora;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.*;

import java.util.HashMap;

public class CognitoClient {

    private AWSCognitoIdentityProvider cognitoClient;

    public CognitoClient() {
        this.cognitoClient = AWSCognitoIdentityProviderClientBuilder.standard()
            .withRegion(System.getenv("AWS_REGION"))
            .build();
    }

    public void signUp(String username, String password, String email) {
        SignUpRequest signUpRequest = new SignUpRequest()
            .withClientId(System.getenv("COGNITO_CLIENT_ID"))
            .withUsername(username)
            .withPassword(password)
            .withUserAttributes(new AttributeType()
                .withName("email")
                .withValue(email));

        try {
            SignUpResult result = cognitoClient.signUp(signUpRequest);
            System.out.println("Sign up result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signIn(String username, String password) {
        AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest()
            .withClientId(System.getenv("COGNITO_CLIENT_ID"))
            .withUserPoolId(System.getenv("COGNITO_USER_POOL_ID")) 
            .withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
            .withAuthParameters(new HashMap<>());

        authRequest.getAuthParameters().put("USERNAME", username);
        authRequest.getAuthParameters().put("PASSWORD", password);

        try {
            AdminInitiateAuthResult authResult = cognitoClient.adminInitiateAuth(authRequest);
            System.out.println("Authentication result: " + authResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
