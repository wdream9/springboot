package com.zk.zkboot;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Login;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.StringTokenizer;


@Slf4j
@SpringBootTest
class ApplicationTests {
	@Autowired
	private CuratorFramework curatorFramework;


	@Test
	void createNode() throws Exception {

		// 添加持久节点
		String path = curatorFramework.create().forPath("/java-create-node");

		// 添加持久有序节点
		String forPath = curatorFramework.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/java-create-node-ps");

		// 添加临时 \ 有序节点
		String path1 = curatorFramework.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/java-create-node", "some-data".getBytes());
		System.out.println(path);
	}
	@Test
	void setData() throws Exception {
		// 给节点添加数据
		Stat stat = curatorFramework.setData().forPath("/java-create-node", "addData".getBytes());
	}
	@Test
	void createWithParent() throws Exception {
		// 父节点不存在，把父节点一起创建
		String pathWithParent = "/java-parent-node/sub-node-1";
		String forPath = curatorFramework.create().creatingParentsIfNeeded().forPath(pathWithParent);
	}

	@Test
	void deleteNode() throws Exception {
		// 删除节点，如果存在子节点也一起删除
		String pathWithParent = "/java-parent-node";
		curatorFramework.delete().guaranteed().deletingChildrenIfNeeded().forPath(pathWithParent);


	}

	@Test
	void addNodeLintener(){
		NodeCache nodeCache = new NodeCache(curatorFramework,"/java-listener");
		nodeCache.getListenable().addListener(new NodeCacheListener() {
			@Override
			public void nodeChanged() throws Exception {
				log.info("node change...");
				// 获取节点的数据
				byte[] bytes = curatorFramework.getData().forPath("/java-listener");
				log.info(new String(bytes));
			}
		});
	}

	@Test
	void curatorReadWriteLock() throws Exception {
		// 读写锁
		InterProcessReadWriteLock interProcessReadWriteLock = new InterProcessReadWriteLock(curatorFramework, "/lock-01");
		InterProcessMutex read = interProcessReadWriteLock.readLock();
		// 获取读锁对象
		/**
		 * 读锁：当资源是读锁时，也可以加上读锁，程序向下执行
		 * 写锁：资源不存在任何锁才能 加锁成功
		 */
		read.acquire();	// 尝试获取读锁
		for(int i = 1;i<100;i++){
			Thread.sleep(3000);
		}
		// 释放锁
		read.release();
	}
	@Test
	void getWriteLock() throws Exception {
		InterProcessReadWriteLock interProcessReadWriteLock = new InterProcessReadWriteLock(curatorFramework, "/lock-01");
		InterProcessMutex interProcessMutex = interProcessReadWriteLock.writeLock();

		interProcessMutex.acquire();	// 尝试获取写锁
		for (int i=1;i<100;i++){
			Thread.sleep(3000);
			System.out.println(i);
		}
		interProcessMutex.release();

	}




}
