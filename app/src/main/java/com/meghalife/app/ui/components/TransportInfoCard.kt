package com.meghalife.app.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import com.meghalife.app.language.t

@Composable
fun TransportInfoCard(
    info: String,
    contacts: List<String>
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Text(
                text = t("🚌 How to Reach"),
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = info,
                style = MaterialTheme.typography.bodyMedium
            )

            contacts.forEach { number ->
                OutlinedButton(
                    onClick = {
                        val intent = Intent(
                            Intent.ACTION_DIAL,
                            Uri.parse("tel:$number")
                        )
                        context.startActivity(intent)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(t("Call") + " $number")
                }
            }
        }
    }
}
