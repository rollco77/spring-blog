package com.millky.blog.domain.model.entity;

import com.millky.blog.domain.model.command.PostCommand;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
public class Contents {

	@Id
	@GeneratedValue
	int id;

	String userId;
	String name;

	@Column(nullable = false)
	String title;

	String subtitle;

	@Column(columnDefinition = "TEXT")
	String content;

	Date regDate;

	Date updateDate;

	private int categoryId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId", insertable = false, updatable = false)
	private Category category;

	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	private List<PostTag> postTagList;

	public Contents(PostCommand postCommand) {
		BeanUtils.copyProperties(postCommand, this);
	}
}
