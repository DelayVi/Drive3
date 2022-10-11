package ru.delayvi.drive3.domain.entity

enum class Color {
    RED {
        override fun toString(): String {
            return "Красный"
        }
    },
    GREEN {
        override fun toString(): String {
            return "Зелёный"
        }
    },
    BLACK {
        override fun toString(): String {
            return "Чёрный"
        }
    },
    BLUE {
        override fun toString(): String {
            return "Синий"
        }
    },
    WHITE {
        override fun toString(): String {
            return "Белый"
        }
    },
    YELLOW {
        override fun toString(): String {
            return "Жёлтый"
        }
    }
}