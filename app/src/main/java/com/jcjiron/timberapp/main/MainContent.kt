package com.jcjiron.timberapp.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun MainContent() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (label, title, shape1, shape2) = createRefs()

        Text(text = "One Gift, One Tree, One Planet",
            modifier = Modifier.constrainAs(label) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(parent.start, margin = 24.dp)
            })
        Text(text = "Plant a billion trees",
            modifier = Modifier.constrainAs(title) {
                top.linkTo(label.bottom, margin = 8.dp)
                start.linkTo(parent.start, margin = 24.dp)
            })
        Surface(modifier = Modifier
            .background(Color.Black)
            .constrainAs(shape1) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .fillMaxWidth()
            .height(600.dp)) {

        }
    }
}

@Preview
@Composable
fun Preview() {
    MainContent()
}