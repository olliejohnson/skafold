package io.oliverj.skaffold.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import androidx.compose.runtime.RecomposeScope
import androidx.compose.runtime.currentRecomposeScope
import io.oliverj.skaffold.Skafold.Companion.getData
import kotlin.reflect.KProperty

class SkafoldedField<T>(id: String, val scope: RecomposeScope) {

    val id: String? = id as String?

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return getData()?.get(id) as T
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        getData()?.set(id!!, value as String)

        scope.invalidate()
    }
}

@Composable
fun <T> skafolded(initialGetter: @DisallowComposableCalls () -> String): SkafoldedField<T> {
    val data = getData()
    return SkafoldedField(initialGetter(), currentRecomposeScope)
}