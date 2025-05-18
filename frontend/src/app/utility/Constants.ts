export class Constants {
    public static readonly AUTHORIZATION: string = "Authorization";
    public static readonly AUTH_PREFIX: string = "Bearer ";

    public static readonly URL_BASE = 'http://localhost:8080/api/v1/';
    public static readonly URL_LOGIN = this.URL_BASE + 'login';

    public static readonly URL_GET_TEAMS = this.URL_BASE + 'getTeams';
    public static readonly URL_GET_TEAM = this.URL_BASE + 'getTeams/';
    public static readonly URL_ADD_TEAM = this.URL_BASE + 'addTeam';
    public static readonly URL_UPDATE_TEAM = this.URL_BASE + 'updateTeam/';
    public static readonly URL_DELETE_TEAM = this.URL_BASE + 'deleteTeam/';
    public static readonly URL_KEEP_FIRST = this.URL_BASE + 'keepFirst/';
    public static readonly URL_SEND_EMAIL = this.URL_BASE + 'sendEmail';
    public static readonly URL_GET_EMAILS = this.URL_BASE + 'getEmails';
    public static readonly URL_GET_EMAIL = this.URL_BASE + 'getEmails/';
    public static readonly URL_GET_USERS = this.URL_BASE + 'getUsers';
    public static readonly URL_GET_USER = this.URL_BASE + 'getUsers/';
    public static readonly URL_ADD_USER = this.URL_BASE + 'addUser';
    public static readonly URL_UPDATE_USER = this.URL_BASE + 'updateUser/';
    public static readonly URL_DELETE_USER = this.URL_BASE + 'deleteUser/';


}