package net.squanchy.service.firebase

import com.google.firebase.firestore.GeoPoint
import net.squanchy.service.firebase.model.conferenceinfo.FirestoreConferenceInfo
import net.squanchy.service.firebase.model.conferenceinfo.FirestoreVenue
import net.squanchy.service.firebase.model.schedule.FirestoreDay
import net.squanchy.service.firebase.model.schedule.FirestoreEvent
import net.squanchy.service.firebase.model.schedule.FirestorePlace
import net.squanchy.service.firebase.model.schedule.FirestoreSchedulePage
import net.squanchy.service.firebase.model.schedule.FirestoreSpeaker
import net.squanchy.service.firebase.model.schedule.FirestoreTrack
import java.util.Date

private val A_DATE = Date(123456789)
private val A_DATE_PLUS_SOME_TIME = Date(123459999)

fun aFirestoreTrack(
    id: String = "a track id",
    name: String = "a track name",
    accentColor: String? = "#ABCDEF",
    textColor: String? = "#FEDCBA",
    iconUrl: String? = "www.squanchy.net"
) = FirestoreTrack().apply {
    this.id = id
    this.name = name
    this.accentColor = accentColor
    this.textColor = textColor
    this.iconUrl = iconUrl
}

fun aFirestoreSpeaker(
    id: String = "bananana",
    name: String = "Banana Joe",
    bio: String = """Joe ¡oh Banana Joe!
Tu tienes, ¡oh Banana nana Joe!
Corazón gigante y alma soñan,te
¡Oh Banana nana Joe!
Joe ¡oh Banana Joe!
Tu eres, ¡oh Banana na na Joe!
Un gran marinero con puños de acero""",
    companyName: String? = "Amantido",
    companyUrl: String? = "http://banana.joe",
    personalUrl: String? = "https://en.wikipedia.org/wiki/Banana_Joe_(film)",
    photoUrl: String? = "https://i.ytimg.com/vi/-1HB26ko2H8/hqdefault.jpg",
    twitterUsername: String? = "@bananaJoe1982"
) = FirestoreSpeaker().apply {
    this.id = id
    this.name = name
    this.bio = bio
    this.companyName = companyName
    this.companyUrl = companyUrl
    this.personalUrl = personalUrl
    this.photoUrl = photoUrl
    this.twitterUsername = twitterUsername
}

fun aFirestorePlace(
    id: String = "banana-room",
    name: String = "The banana room™",
    floor: String? = "Banana floor"
) = FirestorePlace().apply {
    this.id = id
    this.name = name
    this.floor = floor
}

fun aFirestoreDay(
    id: String = "dayId",
    date: Date = A_DATE
) = FirestoreDay().apply {
    this.id = id
    this.date = date
}

fun aFirestoreVenue(
    name: String = "Venue Of The Conference",
    address: String = "Conference rd",
    latLon: GeoPoint = GeoPoint(0.0, 0.0),
    description: String = "This is the conference you're looking for",
    mapUrl: String = "www.maps.google.com/conference",
    timezone: String = "Europe/Rome"
) = FirestoreVenue().apply {
    this.name = name
    this.address = address
    this.latLon = latLon
    this.description = description
    this.mapUrl = mapUrl
    this.timezone = timezone
}

fun aFirestoreEvent(
    id: String = "banana",
    title: String = "Hello \uD83C\uDF4C", // Yes, that's a banana emoji. You never know
    startTime: Date = A_DATE,
    endTime: Date = A_DATE_PLUS_SOME_TIME,
    place: FirestorePlace? = aFirestorePlace(),
    track: FirestoreTrack? = aFirestoreTrack(),
    speakers: List<FirestoreSpeaker> = listOf(aFirestoreSpeaker()),
    experienceLevel: String? = "beginner",
    type: String = "keynote",
    description: String? = "Now this is the story all about how\nMy life got flipped, turned upside down"
) = FirestoreEvent().apply {
    this.id = id
    this.title = title
    this.startTime = startTime
    this.endTime = endTime
    this.place = place
    this.track = track
    this.type = type
    this.speakers = speakers
    this.description = description
    this.experienceLevel = experienceLevel
}

fun aFirestoreSchedulePage(
    day: FirestoreDay = aFirestoreDay(),
    events: List<FirestoreEvent> = (0..3).map { aFirestoreEvent() }
) = FirestoreSchedulePage().apply {
    this.day = day
    this.events = events
}

fun aFirestoreConferenceInfo(
    name: String = "Android Conference",
    socialHashtag: String = "#AndroidIsCool",
    twitterHandle: String = "@ThisIsAFakeConf"
) = FirestoreConferenceInfo().apply {
    this.name = name
    this.socialHashtag = socialHashtag
    this.twitterHandle = twitterHandle
}
