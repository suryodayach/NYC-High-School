package com.suryodayach.nychighschools.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suryodayach.nychighschools.data.model.HighSchool

@Composable
fun HighSchoolItem(
    highSchool: HighSchool,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = modifier.padding(8.dp)
        ) {
            Text(
                highSchool.schoolName,
                style = MaterialTheme.typography.labelLarge
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("DBN: ", style = MaterialTheme.typography.bodySmall)
                Text(
                    highSchool.dbn,
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HighSchoolItemPreview() {
    HighSchoolItem(
        highSchool = HighSchool(
            dbn = "12345",
            schoolName = "Clinton School Writers & Artists, M.S. 260",
            overview = "",
            location = "",
            phoneNumber = "",
            schoolEmail = "",
            city = "Manhattan"
        )
    )
}