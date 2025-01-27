package org.dif.common

enum class Typ(val typ: String) {
    Encrypted("application/didcomm-encrypted+json"),
    Signed("application/didcomm-signed+json"),
    Plaintext("application/didcomm-plain+json");

    companion object {
        fun parse(str: String): Typ = when (str) {
            "application/didcomm-encrypted+json" -> Encrypted
            "application/didcomm-signed+json" -> Signed
            "application/didcomm-plain+json" -> Plaintext
            else -> throw IllegalArgumentException("Unsupported message typ")
        }
    }
}

enum class VerificationMethodType {
    JSON_WEB_KEY_2020,
    X25519_KEY_AGREEMENT_KEY_2019,
    ED25519_VERIFICATION_KEY_2018,
    ECDSA_SECP_256K1_VERIFICATION_KEY_2019,
    OTHER
}

data class VerificationMaterial(
    val format: VerificationMaterialFormat,
    val value: String
)

enum class VerificationMaterialFormat {
    JWK,
    BASE58,
    OTHER
}
