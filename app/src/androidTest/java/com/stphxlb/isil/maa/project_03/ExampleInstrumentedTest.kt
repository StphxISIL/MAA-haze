package com.stphxlb.isil.maa.project_03

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

// Esta sí corre en el celu/emulador porque usa APIs de Android.
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.stphxlb.isil.maa.project_03", appContext.packageName)
    }
}
