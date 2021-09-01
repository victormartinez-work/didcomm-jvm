package org.dif

import com.nimbusds.jose.JWSObjectJSON
import com.nimbusds.jose.util.JSONObjectUtils
import org.dif.fixtures.JWM
import org.dif.fixtures.JWS
import org.dif.mock.DIDDocResolverMock
import org.dif.mock.SecretResolverMock
import org.dif.model.PackSignedParams
import org.dif.model.UnpackParams
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SignedMessageTest {

    @Test
    fun `Test signed message test vectors`() {
        for (test in JWS.TEST_VECTORS) {
            val didComm = DIDComm(DIDDocResolverMock(), SecretResolverMock())

            val packed = didComm.packSigned(
                PackSignedParams.builder(JWM.PLAINTEXT_MESSAGE, test.from).build()
            )

            val unpacked = didComm.unpack(
                UnpackParams.Builder(packed.packedMessage).build()
            )

            val expected = JWSObjectJSON.parse(test.expected)
            val signed = JWSObjectJSON.parse(packed.packedMessage)

            assertEquals(expected.payload.toBase64URL().toString(), signed.payload.toBase64URL().toString())
            assertEquals(expected.header.toString(), signed.header.toString())

            assertEquals(
                JSONObjectUtils.toJSONStringForWeb(JWM.PLAINTEXT_MESSAGE.toJSONObject()),
                JSONObjectUtils.toJSONStringForWeb(unpacked.message.toJSONObject())
            )
        }
    }
}