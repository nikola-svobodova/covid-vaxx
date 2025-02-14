package blue.mild.covid.vaxx.service

import blue.mild.covid.vaxx.dto.internal.IsinGetPatientByParametersResultDto
import blue.mild.covid.vaxx.dto.response.PatientDtoOut

interface IsinServiceInterface {
    suspend fun getPatientByParameters(
        firstName: String,
        lastName: String,
        personalNumber: String
    ): IsinGetPatientByParametersResultDto

    suspend fun tryExportPatientContactInfo(patient: PatientDtoOut, notes: String?): Boolean
}
