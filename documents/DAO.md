DAO
================================

# DAOImpl

## append 

### user talk homework course course_pages

use all except id

(and user also except logged )

### statistics resources homework_submit choose_course

use all,complete



## delete

### user talk homework course course_pages

use id

### statistics

use s_id or c_id or cp_id or some of them to locate and delete

### resources homework_submit choose_course

resources:use c_id or number or both to locate and delete

homework_submit:use hw_id or s_id or both to locate and delete

choose_course:use c_id or s_id or both to locate and delete



## modify

### user talk homework course course_pages

use id to locate ,can modify other information

### statistics choose_course

don't have method 'modify'

### resources homework_submit

resources:use c_id and number to locate,can modify content

homework_submit:use hw_id and s_id to locate,can modify content



## infoList

### user talk statistics resources homework homework_submit course course_pages choose_course

can use any information to search and return all information of rows that meet these conditions 
