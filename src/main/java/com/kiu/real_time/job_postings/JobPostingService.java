package com.kiu.real_time.job_postings;

import com.kiu.real_time.ResourceNotFoundException;
import com.kiu.real_time.person.Owner;
import com.kiu.real_time.person.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;
    private final OwnerRepository ownerRepository;

    public List<JobPosting> getAllJobPostings() {
        return jobPostingRepository.findAll();
    }

    // ID로 단일 공고 조회
    public JobPosting getJobPostingById(Integer id) {
        return jobPostingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("JobPosting not found with id: " + id));
    }

    public JobPostingDto toDto(JobPosting entity) {
        JobPostingDto dto = new JobPostingDto();
        dto.setId(entity.getId());
        dto.setWorkingHours(entity.getWorkingHours());
        dto.setNumberOfWorkers(entity.getNumberOfWorkers());
        dto.setWorkLocation(entity.getWorkLocation());
        dto.setJobDescription(entity.getJobDescription());
        dto.setDailyWage(entity.getDailyWage());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setOwnerId(entity.getOwner().getId());
        dto.setOwnerName(entity.getOwner().getName()); // Owner 엔티티에 getName()이 있다고 가정
        return dto;
    }

    public JobPosting createJobPostingFromDto(JobPostingDto dto) {
        JobPosting entity = new JobPosting();
        entity.setWorkingHours(dto.getWorkingHours());
        entity.setNumberOfWorkers(dto.getNumberOfWorkers());
        entity.setWorkLocation(dto.getWorkLocation());
        entity.setJobDescription(dto.getJobDescription());
        entity.setDailyWage(dto.getDailyWage());
        // Owner는 별도로 조회해서 set
        Owner owner = ownerRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found"));
        entity.setOwner(owner);
        return jobPostingRepository.save(entity);
    }

}
