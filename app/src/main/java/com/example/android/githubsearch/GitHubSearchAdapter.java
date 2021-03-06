package com.example.android.githubsearch;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GitHubSearchAdapter extends RecyclerView.Adapter<GitHubSearchAdapter.SearchResultViewHolder> {
    private GitHubUtils.GitHubRepo[] mRepos;
    OnSearchItemClickListener mSearchItemClickListener;

    public interface OnSearchItemClickListener {
        void onSearchItemClick(GitHubUtils.GitHubRepo repo);
    }

    GitHubSearchAdapter(OnSearchItemClickListener searchItemClickListener){
        mSearchItemClickListener = searchItemClickListener;
    }

    public void updateSearchResults(GitHubUtils.GitHubRepo[] repos) {
        mRepos = repos;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mRepos != null) {
            return mRepos.length;
        } else {
            return 0;
        }
    }

    @NonNull
    @Override
    public SearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.search_result_item, parent, false);
        return new SearchResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultViewHolder holder, int position) {
        holder.bind(mRepos[position]);
    }

    class SearchResultViewHolder extends RecyclerView.ViewHolder {
        private TextView mSearchResultTV;

        public SearchResultViewHolder(View itemView) {
            super(itemView);
            mSearchResultTV = (TextView)itemView.findViewById(R.id.tv_search_result);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    GitHubUtils.GitHubRepo searchResult = mRepos[getAdapterPosition()];
                    mSearchItemClickListener.onSearchItemClick(searchResult);
                }
            });
        }

        public void bind(GitHubUtils.GitHubRepo repo) {
            mSearchResultTV.setText(repo.full_name);
        }
    }
}