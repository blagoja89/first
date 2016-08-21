package by.mavs.news.dao;

import by.mavs.news.dbutil.ConnectionPool;
import by.mavs.news.model.News;

public final class NewsDao extends AbstractDao<News> {
	@Override
	public Class<News> getEntityClass() {
		return News.class;
	}

	@Override
	public ConnectionPool getPool() {
		return pool;
	}

	@Override
	public void setPool(ConnectionPool pool) {
		this.pool = pool;
	}

}
