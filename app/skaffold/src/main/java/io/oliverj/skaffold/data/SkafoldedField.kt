package io.oliverj.skaffold.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import androidx.compose.runtime.RecomposeScope
import androidx.compose.runtime.currentRecomposeScope
import io.oliverj.skaffold.Skafold.Companion.getData
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KProperty
import kotlin.reflect.full.memberProperties

class SkafoldedField<T>(id: String, val scope: RecomposeScope) {

    val id: String? = id as String?

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return getData()!!::class.memberProperties.find { it.name == id }!!.getter.call(getData()) as T
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        val prop = getData()!!::class.memberProperties.find { it.name == id }

        if (prop is KMutableProperty1<*, *>) {
            (prop as KMutableProperty1<PageData, T>).setter.call(getData(), value)
        }

        scope.invalidate()
    }
}

@Composable
fun <T> skafolded(initialGetter: @DisallowComposableCalls () -> String): SkafoldedField<T> {
    val data = getData()
    return SkafoldedField(initialGetter(), currentRecomposeScope)
}