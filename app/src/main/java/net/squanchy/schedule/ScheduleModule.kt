package net.squanchy.schedule

import dagger.Module
import dagger.Provides
import net.squanchy.schedule.tracksfilter.TracksFilter
import net.squanchy.service.firebase.FirebaseAuthService
import net.squanchy.service.firebase.FirestoreDbService
import net.squanchy.support.injection.ChecksumModule
import net.squanchy.support.lang.Checksum

@Module(includes = [ChecksumModule::class])
class ScheduleModule {

    @Provides
    internal fun scheduleService(
        authService: FirebaseAuthService,
        dbService: FirestoreDbService,
        tracksFilter: TracksFilter,
        checksum: Checksum
    ): ScheduleService = FirestoreScheduleService(authService, dbService, tracksFilter, checksum)
}
