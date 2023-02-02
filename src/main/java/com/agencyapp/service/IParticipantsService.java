package com.agencyapp.service;

import java.util.List;

import com.agencyapp.dto.messaging.ParticipantDTO;
import com.agencyapp.model.messaging.ParticipantsEntity;

public interface IParticipantsService {
	public boolean deleteParticipantsByUserID(int userId);
	public List<ParticipantsEntity> findByUserId(int userId);
	public List<ParticipantDTO> getParticipantByUserLogin(int userId);
}
