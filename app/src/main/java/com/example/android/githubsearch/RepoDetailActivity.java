package com.example.android.githubsearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class RepoDetailActivity extends AppCompatActivity {
    private TextView mRepoNameTV;
    private TextView mRepoStarsTV;
    private TextView mRepoDescriptionTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_detail);

        mRepoNameTV = findViewById(R.id.tv_repo_name);
        mRepoStarsTV = findViewById(R.id.tv_repo_stars);
        mRepoDescriptionTV = findViewById(R.id.tv_repo_description);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(GitHubUtils.EXTRA_GITHUB_REPO)) {
            GitHubUtils.GitHubRepo repo = (GitHubUtils.GitHubRepo) intent.getSerializableExtra(GitHubUtils.EXTRA_GITHUB_REPO);
            mRepoNameTV.setText(repo.full_name);
            mRepoStarsTV.setText("" + repo.stargazers_count);
            mRepoDescriptionTV.setText(repo.description);
        }
    }
}
