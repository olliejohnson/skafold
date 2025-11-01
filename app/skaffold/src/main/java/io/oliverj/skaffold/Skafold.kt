package io.oliverj.skaffold

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.oliverj.skaffold.builtin.GamePage
import io.oliverj.skaffold.builtin.HomePage
import kotlinx.serialization.json.Json

class Skafold {
    companion object {
        var currentPage: String = ""

        var pageData: MutableMap<String, MutableMap<String, String>> = mutableMapOf()

        fun getData(): MutableMap<String, String>? {
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

    fun builtin(nav: NavController) {
        "home" page HomePage(nav go "game")

        "game" page GamePage(nav go "auton", nav go "home")
    }

    fun saveData() {
        val json = Json.encodeToString(pageData)
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