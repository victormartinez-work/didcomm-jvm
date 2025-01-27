package org.dif.model

import org.dif.diddoc.DIDDocResolver
import org.dif.message.Message
import org.dif.secret.SecretResolver

/**
 * Pack Plaintext Message Parameters
 */
data class PackPlaintextParams(
    val message: Message,
    val didDocResolver: DIDDocResolver?,
    val secretResolver: SecretResolver?,
) {
    private constructor(builder: Builder) : this(
        builder.message,
        builder.didDocResolver,
        builder.secretResolver
    )

    companion object {
        fun builder(message: Message) = Builder(message)
    }

    /**
     * Creates Pack Plaintext Parameters Builder.
     *
     * @property message The message to be packed into a Plaintext DIDComm message.
     */
    class Builder(val message: Message) {
        var didDocResolver: DIDDocResolver? = null
            private set

        var secretResolver: SecretResolver? = null
            private set

        /**
         * Sets Optional DIDDoc resolver that can override a default DIDDoc resolver.
         *
         * @param didDocResolver Custom DIDDoc resolver
         * @return This builder.
         */
        fun didDocResolver(didDocResolver: DIDDocResolver) = apply { this.didDocResolver = didDocResolver }

        /**
         * Sets Optional Secret resolver that can override a default Secret resolver.
         *
         * @param secretResolver Custom Secret resolver
         * @return This builder.
         */
        fun secretResolver(secretResolver: SecretResolver) = apply { this.secretResolver = secretResolver }

        /**
         * Builds parameters
         *
         * @return Pack Plaintext Message Parameters
         */
        fun build() = PackPlaintextParams(this)
    }
}
