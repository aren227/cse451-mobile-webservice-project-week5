package com.aren227.mycalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.aren227.mycalculator.ui.theme.MyCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyCalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculatorScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

enum class Operator(val symbol: String) {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/")
}

private fun Calculator.calculate(x: Double, operator: Operator, y: Double): Double =
    when (operator) {
        Operator.ADD -> add(x, y)
        Operator.SUBTRACT -> subtract(x, y)
        Operator.MULTIPLY -> multiply(x, y)
        Operator.DIVIDE -> divide(x, y)
    }

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {
    val calculator = remember { Calculator() }
    var a by remember { mutableStateOf("") }
    var b by remember { mutableStateOf("") }
    var operator by remember { mutableStateOf(Operator.ADD) }
    var expanded by remember { mutableStateOf(false) }
    var result by remember { mutableStateOf("") }

    val x = a.toDoubleOrNull()
    val y = b.toDoubleOrNull()

    Column(modifier = modifier) {
        TextField(
            value = a,
            onValueChange = {
                a = it
                result = ""
            },
            label = { Text("A") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )
        Box {
            Button(onClick = { expanded = true }) {
                Text(operator.symbol)
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                Operator.entries.forEach {
                    DropdownMenuItem(
                        text = { Text(it.symbol) },
                        onClick = {
                            operator = it
                            expanded = false
                            result = ""
                        }
                    )
                }
            }
        }
        TextField(
            value = b,
            onValueChange = {
                b = it
                result = ""
            },
            label = { Text("B") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )
        Button(
            onClick = {
                if (x != null && y != null) {
                    result = calculator.calculate(x, operator, y).toString()
                }
            },
            enabled = x != null && y != null
        ) {
            Text("=")
        }
        Text(result)
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorScreenPreview() {
    MyCalculatorTheme {
        CalculatorScreen()
    }
}
