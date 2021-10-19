package itz.peaxel.asuka

import itz.peaxel.asuka.core.AConfiguration
import itz.peaxel.asuka.utils.ALogger
import kotlin.system.exitProcess

object Asuka {

    val config = AConfiguration()

    private val logger = ALogger(this::class.java.simpleName)

    var isSigma = false
    var isInvisible = false

    val getInvisibleStatus: Boolean
    get() = isInvisible

    private fun start(args: Array<String>) {

        for (arg in args) {
            when (arg) {
                "-sigma" -> {
                    isSigma = true
                    logger.warning("Sigma is enabled ! Asuka will not work as usual, and bugs may appear.")
                    logger.debug("All sigma functionalities are unlocked. Do at your own risk.")
                }
                "-invisible" -> {
                    isInvisible = true
                    logger.warning("Invisible argument activated ! The bot will never appear online.")
                }
            }
        }
        logger.system("Starting config initialization...")
        config.init()

        logger.system("Starting config loading...")
        config.load()
        println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-")
        logger.system("Asuka v.${config.version} is ready to use ! type 'help' for fore infos !")
        println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-")
    }

    fun stop() {
        logger.warning("Asuka's running process shutting down. Saving...")
        config.save()
        exitProcess(0)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        start(args)
    }
}