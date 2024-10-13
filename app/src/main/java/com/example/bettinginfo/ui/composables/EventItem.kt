package com.example.bettinginfo.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bettinginfo.R
import com.example.bettinginfo.data.ui_model.Event
import com.example.bettinginfo.data.ui_model.Odds

@Composable
fun EventItem(event: Event) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(
                MaterialTheme.colorScheme.primaryContainer,
                shape = MaterialTheme.shapes.medium
            )
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = event.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = event.date,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1
            )
        }

    EventInfo(
        region = event.region,
        sport = event.sport,
        league = event.league
    )
        HorizontalDivider(Modifier.padding(vertical = 8.dp))
        Odds(event.odds)
    }
}

@Composable
private fun EventInfo(
    region: String,
    sport: String,
    league: String
){
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        InfoRow(text = region, label = stringResource(id = R.string.region))
        InfoRow(text = sport, label = stringResource(id = R.string.sport))
        InfoRow(text = league, label = stringResource(id = R.string.league))
    }
}

@Composable
private fun InfoRow(text: String, label: String){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$label: ",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Composable
private fun Odds(odds: Odds){
    Row(
        modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            Modifier.weight(.33f).fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            OddText(odd = odds.odd1, stringResource(id = R.string.win))
            OddText(odd = odds.oddX1, stringResource(id = R.string.win_draw))
        }
        Column(
            Modifier.weight(.33f).fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OddText(odd = odds.oddX, stringResource(id = R.string.draw))
            OddText(odd = odds.oddX2, stringResource(id = R.string.lose_draw))
        }
        Column(
            Modifier.weight(.33f).fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OddText(odd = odds.odd2, stringResource(id = R.string.lose))
            OddText(odd = odds.odd12, stringResource(id = R.string.win_lose))
        }
    }
}

@Composable
private fun OddText(odd: Double?, label: String){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = odd?.toString() ?: "-",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
        )
    }
}

@Preview(showBackground = true, widthDp = 250)
@Composable
private fun EventItemPreview() {
    val sampleEvent = Event(
        title = "Championship Match",
        region = "Europe",
        date = "2024-10-12",
        league = "Premier League",
        sport = "Football",
        odds = Odds(
            odd1 = 1.5,
            oddX = 3.0,
            odd2 = 2.5,
            oddX1 = 1.8,
            oddX2 = null,
            odd12 = 2.2
        )
    )

    EventItem(event = sampleEvent)
}