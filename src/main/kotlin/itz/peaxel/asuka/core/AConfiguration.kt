package itz.peaxel.asuka.core

import itz.peaxel.asuka.Asuka
import itz.peaxel.asuka.core.managers.CommandManager
import itz.peaxel.asuka.core.managers.JDAManager
import itz.peaxel.asuka.core.managers.ModuleManager
import itz.peaxel.asuka.utils.ALogger
import org.ini4j.Ini
import java.io.File

class AConfiguration {

    private val logger = ALogger(this::class.java.simpleName)

    private lateinit var token: String
    private lateinit var motd: String
    lateinit var ownerIds: List<String>

    val version: String
        get() = "0.2.0 ALPHA-1"

    fun init(){
        val configFile = File("config.ini")

        if(!configFile.exists()){
            logger.error("Config file not found ! Creating file...\n-----> Please configure the newly created one.")
            val ini = Ini(configFile)
            ini.put("configuration", "token", "insert token here")
            ini.put("configuration", "owner_ids", "insert owner ids separated by a space here")
            ini.put("configuration", "motd", "insert an motd here")
            ini.store()
            Asuka.stop()
        }

        val ini = Ini(configFile)

        token = ini.get("configuration", "token")
        ownerIds = ini.get("configuration", "owner_ids").split(" ")
        motd = ini.get("configuration", "motd")
    }

    fun load(){
        JDAManager.startJda(token, motd)
        CommandManager.loadCommands()
        ModuleManager.startModules()
    }

    fun save(){
        val configFile = File("config.ini")
    }
}