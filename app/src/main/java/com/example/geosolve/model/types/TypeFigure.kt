package com.example.geosolve.model.types

import com.example.geosolve.model.figure.Figure

interface TypeFigure {
    fun isMatch(figure: Figure): Boolean
    fun solve(figure: Figure)
}