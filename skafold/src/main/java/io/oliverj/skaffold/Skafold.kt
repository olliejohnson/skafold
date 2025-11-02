package io.oliverj.skaffold

import android.content.Context
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
import java.io.File
import java.io.FileOutputStream
import kotlin.reflect.KClass
import kotlin.reflect.full.companionObject
import kotlin.reflect.full.companionObjectInstance
import kotlin.reflect.full.functions
import kotlin.reflect.full.memberProperties

/**
 * This is the main wrapper for the skafold framework. Everything
 * you do inside this framework will happen inside the context of this function.
 * This is where you register pages and setup navigation.
 *
 * @author Oliver Johnson
 */
class Skafold(val context: Context, val nav: NavController) {
    companion object {
        var currentPage: String = ""

        var pageData: MutableMap<String, PageData> = mutableMapOf()

        fun getData(): PageData? {
            return pageData[currentPage]
        }
    }

    var pages: MutableMap<String, Page> = mutableMapOf()

    /**
     * An infix function used to make writing navigation easier. This
     * function also updates the [currentPage].
     *
     * @author Oliver Johnson
     */
    infix fun NavController.go(id: String): () -> Unit {
        return { this.navigate(id); currentPage = id }
    }

    /**
     * An infix function used to make registering pages easier.
     *
     * Example:
     *
     *   `"home" page HomePage(nav go "game") data HomeData()`
     *
     * @param page The page to set for the id
     * @return The created page
     * @author Oliver Johnson
     */
    infix fun String.page(page: Page): Page {
        pages[this] = page
        page.id = this
        return page
    }

    /**
     * An infix function used to set the data object for the page.
     *
     * @param state The [PageData] to use for the [Page]
     * @author Oliver Johnson
     */
    infix fun <T : PageData> Page.data(state: T) {
        pageData[this.id] = state
    }

    /**
     * This function creates the builtin pages. If you do not want to use the builtin pages then
     * do not add this function do your skafold definition.
     *
     * @author Oliver Johnson
     */
    fun builtin() {
        "home" page HomePage(nav go "game") data HomeData()

        "game" page GamePage(nav go "auton", nav go "home") data GameData()
    }

    /**
     * This function when called will save all page data to a json file.
     *
     * @author Oliver Johnson
     */
    fun saveData() {
        val eMap: MutableMap<String, JsonElement> = mutableMapOf()

        val elements = pageData.values.map { it ->
            val pack = it::class.java.`package`!!.name + ".generated"
            val classPath = pack + "." + it::class.simpleName + "Ext"

            val cls = Companion::class.java.classLoader!!.loadClass(classPath).kotlin

            val funcs = cls.companionObject?.functions
            println(funcs?.map { it.name })
            val element = funcs?.find { it.name == "save" }?.call(cls.companionObjectInstance, it) as JsonElement

            val fields = (it::class as KClass<Any>).memberProperties

            val nameField = fields.first { it.name == "name" }

            val name = nameField.get(it) as String

            eMap[name] = element

            element
        }

        val format = Json { encodeDefaults = true }
        val json = format.encodeToString(eMap)

        saveFile("matches", "demo.json", json)
    }

    /**
     * This will save a string to a file in the application data folder.
     *
     * @author Oliver Johnson
     */
    fun saveFile(directory: String? = null, fileName: String, content: String) {
        val location = context.getExternalFilesDir(directory)
        if (!location!!.exists())
            location.mkdirs()
        val file = File(location, fileName)

        if (file.exists())
            file.delete()

        try {
            FileOutputStream(file).use { fos ->
                fos.write(content.toByteArray())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

/**
 * This is the function used to create a new skafold app.
 *
 * @param context The application context
 * @param modifier The compose modifier
 * @param builder The skafold builder
 * @author Oliver Johnson
 */
@Composable
fun skafold(context: Context, modifier: Modifier = Modifier, builder: Skafold.() -> Unit) {
    val nav = rememberNavController()

    val inst = Skafold(context, nav)
    builder(inst)

    Skafold.currentPage = inst.pages.keys.first()
    NavHost(nav, startDestination = inst.pages.keys.first()) {
        inst.pages.forEach { (key, value) ->
            composable(key) {value.Core({inst.saveData()}, modifier)}
        }
    }
}