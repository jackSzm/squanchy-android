package net.squanchy.schedule.domain.view

import net.squanchy.support.system.CurrentTime
import org.joda.time.DateTimeZone

private const val NOT_FOUND_INDEX = -1
private const val FIRST_PAGE_INDEX = 0

private const val CURRENT_SLOT_THRESHOLD = .6f

data class Schedule(val pages: List<SchedulePage>, val timeZone: DateTimeZone) {

    val isEmpty: Boolean
        get() = pages.all { it.events.isEmpty() }

    fun findTodayIndexOrDefault(currentTime: CurrentTime): Int {
        val now = currentTime.currentDateTime().withZone(timeZone)
        return pages
            .indexOfFirst { page ->
                page.date.isEqual(now.toLocalDate())
            }
            .let {
                when (it) {
                    NOT_FOUND_INDEX -> FIRST_PAGE_INDEX
                    else -> it
                }
            }
    }

    fun findNextEventForPage(page: SchedulePage, currentTime: CurrentTime) =
        page.events
            .firstOrNull { event ->
                val startDateTime = event.startTime.toDateTime().withZone(event.timeZone)
                val currentDateTime = currentTime.currentDateTime().toDateTime().withZone(event.timeZone)
                val endDateTime = event.endTime.toDateTime().withZone(event.timeZone)

                if (currentDateTime.isAfter(endDateTime)) {
                    false
                } else {
                    val duration = endDateTime.millis - startDateTime.millis
                    val offset = currentDateTime.millis - startDateTime.millis

                    offset.toFloat() / duration < CURRENT_SLOT_THRESHOLD
                }
            }
}
