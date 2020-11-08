export MYSQL_USER=root
export MYSQL_PASSWORD=root
export MYSQL_DATABASE=LoanDB
if [[ -z "${MYSQL_CI_URL}" ]]; then
	export MYSQL_CI_URL=jdbc:mysql://localhost:3306/LoanDB
fi
