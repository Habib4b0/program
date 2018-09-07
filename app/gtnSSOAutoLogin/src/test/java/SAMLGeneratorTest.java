import com.stpl.sso.saml.StplSAMLRequestGenerator;

import org.junit.jupiter.api.Test;

public class SAMLGeneratorTest {

    // @Test
    public void testGenerate() {

        StplSAMLRequestGenerator gee = new StplSAMLRequestGenerator();
        System.out.println(gee.getAuthNRedirectUrl());

    }
}