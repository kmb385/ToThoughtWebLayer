package org.tothought.github;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.egit.github.core.IRepositoryIdProvider;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tothought.entities.Commit;
import org.tothought.entities.Tag;

public class GitHubService {
	
	private Logger logger = LoggerFactory.getLogger(GitHubService.class);
	
	private GitHubClient client;
	private RepositoryService repoService;
	private CommitService commitService;
	private String user;
	private List<Commit> commits = new ArrayList<Commit>();

	public GitHubService(String user, String password) {
		this.user = user;
		this.client = new GitHubClient();
		this.client.setCredentials(user, password);
		this.repoService = new RepositoryService(this.client);
		this.commitService = new CommitService(this.client);
	}

	public void pullCommits() {
		List<Repository> repos;
		try {
			repos = repoService.getRepositories();
			for (Repository repo : repos)
			{	
				IRepositoryIdProvider repoId = new RepositoryId(this.user, repo.getName());
				List<RepositoryCommit> ghCommits = commitService.getCommits(repoId);

				for (RepositoryCommit ghCommit : ghCommits)
				{
					String[] tokens = ghCommit.getCommit().getMessage().split("\\r?\\n");

					for (String token : tokens) {
						this.commits.add(new Commit(ghCommit, token));
					}
				}
			}
		} catch (IOException e) {
			logger.error("Could not pull commits from GitHub.");
			e.printStackTrace();
		}

	}

	public List<org.tothought.entities.Commit> getCommitsByTag(Tag tag) {
		List<Commit> matches = new ArrayList<Commit>();
		
		if(this.commits.size() == 0){
			this.pullCommits();
		}
		
		for(Commit commit:this.commits){
			if(commit != null && tag != null && commit.getMessage() != null && StringUtils.containsIgnoreCase(commit.getMessage(), tag.getName()))
			{
				//Taking advantage of reference to add tag
				commit.getTags().add(tag);
				matches.add(commit);					
			}
		}
		
		return matches;
	}
}
