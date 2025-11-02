package io.oliverj.skaffold

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.oliverj.skaffold.builtin.GamePage
import io.oliverj.skaffold.builtin.HomePage
import io.oliverj.skaffold.data.GameData
import io.oliverj.skaffold.data.HomeData
import io.oliverj.skaffold.data.PageData
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlin.reflect.full.companionObject
import kotlin.reflect.full.companionObjectInstance
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.declaredMemberExtensionFunctions
import kotlin.reflect.full.functions
import kotlin.reflect.full.memberExtensionFunctions

class Skafold {
    companion object {
        var currentPage: String = ""

        var pageData: MutableMap<String, PageData> = mutableMapOf()

        fun getData(): PageData? {
            return pageData[currentPage]
        }
    }

    var pages: MutableMap<String, Page> = mutableMapOf()

    infix fun NavController.go(id: String): () -> Unit {
        return { this.navigate(id); currentPage = id }
    }

    infix fun String.page(page: Page): Page {
        pages[this] = page
        page.id = this
        return page
    }

    infix fun <T : PageData> Page.data(state: T) {
        pageData[this.id] = state
    }

    fun builtin(nav: NavController) {
        "home" page HomePage(nav go "game") data HomeData()

        "game" page GamePage(nav go "auton", nav go "home") data GameData()
    }

    fun saveData() {
        val elements = pageData.values.map { it ->
            val pack = it::class.java.`package`!!.name + ".generated"
            val classPath = pack + "." + it::class.simpleName + "Ext"

            val cls = Companion::class.java.classLoader!!.loadClass(classPath).kotlin

            val funcs = cls.companionObject?.functions
            println(funcs?.map { it.name })
            funcs?.find { it.name == "save" }?.call(cls.companionObjectInstance, it) as JsonElement
        }
        val format = Json { encodeDefaults = true }
        val json = format.encodeToString(elements)
        println(json)
    }
}

@Composable
fun skafold(modifier: Modifier = Modifier, builder: Skafold.(NavController) -> Unit) {
    val nav = rememberNavController()

    val inst = Skafold()
    builder(inst, nav)

    Skafold.currentPage = inst.pages.keys.first()
    NavHost(nav, startDestination = inst.pages.keys.first()) {
        inst.pages.forEach { (key, value) ->
            composable(key) {value.Core({inst.saveData()}, modifier)}
        }
    }
}