package com.tah.housewarming.service;

import com.tah.housewarming.domain.Link;
import com.tah.housewarming.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LinkService {
    private final LinkRepository repository;

    public List<Link> create(List<Link> links) {
        return repository.saveAll(links);
    }

    public void delete(List<Link> links) {
        areLinksValid(links);
        this.repository.deleteAll(links);
    }

    private void areLinksValid(List<Link> links) {
        links.forEach((Link link) -> this.repository.findById(link.getId()));
    }
}
