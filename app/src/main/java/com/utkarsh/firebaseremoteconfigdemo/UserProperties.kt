package com.utkarsh.firebaseremoteconfigdemo

interface UserProperties {
    companion object {
        const val LANGUAGE = "Lan"
        const val OS = "OS"
        const val PLATFORM = "Android"
    }

    interface Languages {
        companion object {
            const val ENGLISH = "en"
            const val HINDI = "hi"
            const val GUJARATI = "guj"
        }
    }

    interface ServerLanguagesIDS {
        companion object {
            const val ENGLISH = "1"
            const val HINDI = "2"
            const val GUJARATI = "3"
        }
    }
}
