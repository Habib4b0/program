import com.stpl.sso.saml.StplSAMLRequestGenerator;

public class SAMLGeneratorTest {

    // @Test
    public void testGenerate() {

        StplSAMLRequestGenerator gee = new StplSAMLRequestGenerator();
        System.out.println(gee.getAuthNRedirectUrl());

    }

    // @Test
    public void testLogoutGenerate() {

        // new StplSAMLLogoutRequestGenerator(/).buildLogOutRequest();

    }
}